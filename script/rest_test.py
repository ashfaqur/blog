import requests


def test_hello_world():
    url = "http://localhost:7075/blog/api/test"
    response = requests.get(url)    

    assert response.status_code == 200 

    data = response.json()
    assert isinstance(data, dict)
    assert "message" in data
    assert "Hello World" in data["message"]
