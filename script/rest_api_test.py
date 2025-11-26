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

# REST API helper functions


def get_posts() -> list:
    """List of all posts"""
    response = requests.get(POSTS_URL)
    logging.info(
        f"GET Status: {response.status_code}, Response: {response.json()}")
    assert response.status_code == 200
    posts = response.json()
    assert isinstance(posts, list)
    logging.info("GET request successful")
    return posts


def get_post(id: str) -> dict:
    """A single posts"""
    response = requests.get(f"{POSTS_URL}/{id}")
    logging.info(
        f"GET /postId Status: {response.status_code}, Response: {response.json()}")
    assert response.status_code == 200
    post = response.json()
    assert isinstance(post, dict)
    logging.info("GET /postId request successful")
    return post


def delete_post(id: str):
    """Delete a post"""
    response = requests.delete(f"{POSTS_URL}/{id}")
    logging.info(f"DELETE /postId status: {response.status_code}")
    assert response.status_code == 204
    logging.info("DELETE request successful")


def create_post(post_data: dict) -> dict:
    """Create a post"""
    response = requests.post(POSTS_URL, json=post_data)
    logging.info(
        f"POST status: {response.status_code}, Response: {response.json()}")
    assert response.status_code == 201
    post = response.json()
    assert "id" in post
    assert "author" in post
    assert "date" in post
    assert "content" in post
    return post


def update_post(id: str, post_data: dict) -> dict:
    """Update a post"""
    response = requests.put(f"{POSTS_URL}/{id}", json=post_data)
    logging.info(
        f"PUT status: {response.status_code}, Response: {response.json()}")
    assert response.status_code == 200
    post = response.json()
    assert "id" in post
    assert "author" in post
    assert "date" in post
    assert "content" in post
    return post


# Test pre-run setup

@pytest.fixture(scope="function")
def setup():
    """Ensure tests start with no posts"""
    posts = get_posts()
    if posts:
        logging.info("Remove any existing posts before tests")
    for post in posts:
        assert "id" in post
        delete_post(post["id"])
    yield

# Test


def test_rest_api(setup):
    logging.info("-----------------------------------------------")
    logging.info("Test no posts exists")
    posts = get_posts()
    assert len(posts) == 0
    logging.info("Test no posts exists is successful")

    logging.info("-----------------------------------------------")
    logging.info("Test create a new post")
    new_post_data: dict = {
        "author": "Jackson Dane",
        "content": "Custom new blog post",
        "date": "2025-06-16T10:30:00Z"
    }
    created_post = create_post(new_post_data)
    assert created_post["author"] == new_post_data["author"]
    assert created_post["content"] == new_post_data["content"]
    assert created_post["date"] == new_post_data["date"]
    created_post_id = created_post["id"]
    logging.info("Test creation of a new post is successful")

    logging.info("-----------------------------------------------")
    logging.info("Test get a new post")
    existing_post = get_post(created_post_id)
    assert created_post["id"] == existing_post["id"]
    assert created_post["author"] == existing_post["author"]
    assert created_post["content"] == existing_post["content"]
    assert created_post["date"] == existing_post["date"]
    logging.info("Test get a single post is successful")

    logging.info("-----------------------------------------------")
    logging.info("Test update a post")
    update_post_data: dict = {
        "author": "J Dane",
        "content": "Updated the blog post",
        "date": "2025-07-16T11:30:00Z"
    }
    updated_post = update_post(created_post_id, update_post_data)
    assert updated_post["id"] == created_post_id
    assert updated_post["author"] == update_post_data["author"]
    assert updated_post["content"] == update_post_data["content"]
    assert updated_post["date"] == update_post_data["date"]
    logging.info("Test update a post is successful")

    logging.info("-----------------------------------------------")
    logging.info("Test get all posts to check on the updated post")
    posts = get_posts()
    assert len(posts) == 1
    assert updated_post == posts[0]
    logging.info("Test get all posts to check the updated post is successful")

    logging.info("-----------------------------------------------")
    logging.info("Test delete the post")
    delete_post(created_post_id)
    posts = get_posts()
    assert len(posts) == 0
    logging.info("Test delete the post is successful")
    logging.info("-----------------------------------------------")
