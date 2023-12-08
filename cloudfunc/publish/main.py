import functions_framework
import mysql.connector
from google.cloud import secretmanager

table = "sensor_data"
project = "macro-parity-403112"
db_host = "34.134.109.124"


def get_secret(project_id: str, secret_id: str) -> str:
    client = secretmanager.SecretManagerServiceClient()

    name = client.secret_version_path(project_id, secret_id, "latest")

    response = client.access_secret_version(name=name)

    return response.payload.data.decode("UTF-8")


mysql_user = get_secret(project, "mysql-user")
mysql_password = get_secret(project, "mysql-password")


@functions_framework.cloud_event
def hello_pubsub(cloud_event):
    publish_to_table(cloud_event.data["message"]["attributes"])


def publish_to_table(data: dict):
    query = """
    INSERT INTO sensor_data (datetime, sensor_type, sensor_value)
    VALUES (%s, %s, %s);
    """

    conn = mysql.connector.connect(
        host=db_host,
        user=mysql_user,
        password=mysql_password,
        database="sensor_data",
    )

    cursor = conn.cursor()

    type: str = ""
    value: str = ""
    if "temperature" in data:
        type = "temperature"
        value = data["temperature"]
    elif "humidity" in data:
        type = "humidity"
        value = data["humidity"]
    elif "light" in data:
        type = "light"
        value = data["light"]

    values = (data["datetime"], type, value)

    cursor.execute(query, values)

    conn.commit()

    cursor.close()
    conn.close()
