from typing import Callable
import os
import time
import asyncio
import random
from google.cloud import pubsub_v1

sensor_types = ["0", "1", "2"]

credentials_path = "/keys/pubsub.json"
os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = credentials_path


project_id = os.getenv("PROJECT_ID", "macro-parity-403112")
topic_id = os.getenv("SENSOR_TOPIC_ID", "sensor-data")


publisher = pubsub_v1.PublisherClient()
topic = os.getenv("SENSOR_TOPIC", publisher.topic_path(project_id, topic_id))
interval = os.getenv("SENSOR_INTERVAL_MS", 1000)

sensor_type = os.getenv("SENSOR_TYPE", sensor_types[random.randint(0, 2)])


async def send_data(client: pubsub_v1.PublisherClient, data: dict):
    await asyncio.wrap_future(client.publish(topic, "sensor".encode(), **data))
    print(f"Sent data: {data}")


async def sensor_main(get_data: Callable[[], dict]):
    interval_ms = int(interval)

    loop = asyncio.get_running_loop()

    while True:
        data = get_data()
        data["datetime"] = time.strftime("%Y-%m-%d %H:%M:%S")
        loop.create_task(send_data(publisher, data))
        await asyncio.sleep(interval_ms / 1000.0)


if __name__ == "__main__":
    get_data: Callable[[], dict]
    print(f"Sensor type: {sensor_type}")

    try:
        publisher.create_topic(request={"name": topic})
    except Exception as e:
        print(f"An error occurred while creating the topic: {e}")

    if sensor_type == "0":
        get_data = lambda: {"temperature": str(23.4 + random.random() * 0.5)}
    elif sensor_type == "1":
        get_data = lambda: {"humidity": str(50.0 + random.random() * 2.0)}
    else:
        get_data = lambda: {"light": str(100.0 + random.random() * 10.0)}

    asyncio.run(sensor_main(get_data))
