import functions_framework
import mysql.connector
import json
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


@functions_framework.http
def get_table_values(request):
    query = """
    SELECT * FROM sensor_data;
    """

    conn = mysql.connector.connect(
        host=db_host,
        user=mysql_user,
        password=mysql_password,
        database="sensor_data",
    )

    cursor = conn.cursor()

    cursor.execute(query)

    rows = []
    for row in cursor:
        datetime = row[0]
        sensor_type = row[1]
        sensor_value = row[2]

        row_value = {
            "datetime": datetime.strftime("%Y-%m-%d %H:%M:%S"),
            sensor_type: sensor_value,
        }
        rows.append(row_value)

    cursor.close()
    conn.close()

    return json.dumps(rows, indent=4), 200, {"Content-Type": "application/json"}
