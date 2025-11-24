import os
import logging
import requests

BASE_URL = os.getenv("BASE_URL", "http://localhost:7075")

def test_hello_world():
    logging.info("Testing hello world rest endpoint")
    url = f"{BASE_URL}/blog/api/test"
    response = requests.get(url)    

    assert response.status_code == 200 

    data = response.json()
    assert isinstance(data, dict)
    assert "message" in data
    assert "Hello World" in data["message"]


    