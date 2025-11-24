import os
import logging
import requests

BASE_URL = os.getenv("BASE_URL", "http://localhost:7075")

logging.basicConfig(
    format="%(asctime)s %(levelname)-8s %(message)s",
    level=logging.INFO,
    datefmt="%H:%M:%S",
)

def test_hello_world():
    logging.info("Testing hello world rest endpoint")
    url = f"{BASE_URL}/posts/test"
    logging.info(f"URL: {url}")
    response = requests.get(url)    

    assert response.status_code == 200 

    data = response.json()
    assert isinstance(data, dict)
    assert "message" in data
    assert "Hello World" in data["message"]


    