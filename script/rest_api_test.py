import os
import logging
import requests
import pytest

BASE_URL = os.getenv("BASE_URL", "http://localhost:7075")
POSTS_URL = f"{BASE_URL}/posts"

logging.basicConfig(
    format="%(asctime)s %(levelname)-8s %(message)s",
    level=logging.INFO,
    datefmt="%H:%M:%S",
)

def get_posts() -> list:
    """List of all posts"""
    response = requests.get(POSTS_URL)
    logging.info(f"GET Status: {response.status_code}, Response: {response.json()}")
    assert response.status_code == 200
    posts = response.json()
    assert isinstance(posts, list)
    logging.info("GET request successful")
    return posts

def get_post(id: str) -> dict:
    """A single posts"""
    response = requests.get(f"{POSTS_URL}/{id}")
    logging.info(f"GET /postId Status: {response.status_code}, Response: {response.json()}")
    assert response.status_code == 200
    post = response.json()
    assert isinstance(post, dict)
    logging.info("GET /postId request successful")
    return post

def delete_post(id: str):
    """Delete a post"""
    response = requests.delete(f"{POSTS_URL}/{id}")
    logging.info(f"DELETE /{id} status: {response.status_code}")
    assert response.status_code == 204
    logging.info("DELETE request successful")

def create_post(post_data):
    """Create a post"""
    response = requests.post(POSTS_URL, json=post_data)



@pytest.fixture(scope="function")
def setup():
    """Ensure tests start with no posts"""
    posts = get_posts()
    if posts:
        logging.info("Remove existing posts before tests")
    for post in posts:
        assert "id" in post
        delete_post(post["id"])
    yield


def test_rest_api(setup):
    logging.info("---------------------------------------------------------------------")
    # logging.info("Test GET /posts")
    # logging.info("Get the example posts in server")
    # url = f"{BASE_URL}/posts"
    # response = requests.get(url)
    # logging.info(f"Status: {response.status_code}")
    # logging.info(f"Response: {response.json()}")
    # assert response.status_code == 200
    # data = response.json()
    # assert isinstance(data, dict)
    # assert "description" in data
    # assert "A list of posts" in data["description"]
    # assert "posts" in data
    # assert isinstance(data["posts"], list)
    # logging.info("Get request successful")

    # logging.info("---------------------------------------------------------------------")
    # logging.info("Delete any existing posts")

    # for post in data["posts"]:
    #     assert "id" in post
    #     logging.info(f"Deleting post with id {post['id']}")
    #     response = requests.delete(f"{url}/{post['id']}")
    #     logging.info(f"Status: {response.status_code}")
    #     assert response.status_code == 204
    #     logging.info("Delete request successful")
    
    # logging.info("---------------------------------------------------------------------")
    # logging.info("Create a new post")

    # new_post: dict = {
    #     "author": "Jackson Dane",
    #     "content": "Custom new blog post",
    #     "date": "2025-06-16T10:30:00Z"
    #     }
    # logging.info("New post:")
    # logging.info(new_post)
    # logging.info("---------------------------------------------------------------------")
    # logging.info("Test POST /posts")
    # response = requests.post(url, json=new_post)
    # logging.info(f"Status: {response.status_code}")
    # logging.info(f"Response: {response.json()}")
    # assert response.status_code == 201
    # data = response.json()
    # assert isinstance(data, dict)
    # assert "description" in data
    # assert "Post created" in data["description"]
    # assert "post" in data
    # created_post = data["post"]
    # assert "id" in created_post
    # assert "author" in created_post
    # assert "date" in created_post
    # assert "content" in created_post
    # assert created_post["author"] == new_post["author"] 
    # assert created_post["content"] == new_post["content"] 
    # assert created_post["date"] == new_post["date"]
    # id_created_post = created_post["id"]
    # logging.info("Post request successful")
    # logging.info("---------------------------------------------------------------------")
    # logging.info("Test PUT /posts/id")
    # update_post: dict = {
    #     "author": "Jackson Dane",
    #     "content": "Updated the blog post",
    #     "date": "2025-07-16T11:30:00Z"
    #     }
    # logging.info("Update post:")
    # logging.info(update_post)
    # response = requests.put(f"{url}/{id_created_post}", json=update_post)
    # logging.info(f"Status: {response.status_code}")
    # logging.info(f"Response: {response.json()}")
    # assert response.status_code == 200
    # data = response.json()
    # assert isinstance(data, dict)
    # assert "description" in data
    # assert "Post updated" in data["description"]
    # assert "post" in data
    # updated_post = data["post"]
    # assert "id" in updated_post
    # assert "author" in updated_post
    # assert "date" in updated_post
    # assert "content" in updated_post
    # assert updated_post["id"] == id_created_post
    # assert updated_post["author"] == update_post["author"] 
    # assert updated_post["content"] == update_post["content"] 
    # assert updated_post["date"] == update_post["date"]
    # logging.info("---------------------------------------------------------------------")
    # logging.info("Test GET /posts")
    # response = requests.get(url)
    # logging.info(f"Status: {response.status_code}")
    # logging.info(f"Response: {response.json()}")
    # assert response.status_code == 200
    # data = response.json()
    # assert isinstance(data, dict)
    # assert "description" in data
    # assert "A list of posts" in data["description"]
    # assert "posts" in data
    # assert isinstance(data["posts"], list)
    # assert len(data["posts"]) == 1
    # post = data["posts"][0]
    # assert "id" in post
    # assert "author" in post
    # assert "date" in post
    # assert "content" in post
    # assert updated_post["id"] == post["id"]
    # assert updated_post["author"] == post["author"] 
    # assert updated_post["content"] == post["content"] 
    # assert updated_post["date"] == post["date"]
    # logging.info("Get request successful")
    # logging.info("---------------------------------------------------------------------")
    # logging.info("Test DELETE /posts/id")
    # logging.info(f"Deleting post with id {post['id']}")
    # response = requests.delete(f"{url}/{post['id']}")
    # logging.info(f"Status: {response.status_code}")
    # assert response.status_code == 204
    # logging.info("Delete request successful")
    


    


    