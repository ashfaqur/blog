import argparse
import logging
from rest_test import RestTest

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Test server app")
    parser.add_argument("url", type=str, help="Server url")

    args = parser.parse_args()
    logging.basicConfig(
        format="%(asctime)s %(levelname)-8s %(message)s",
        level=logging.INFO,
        datefmt="%H:%M:%S",
    )
    client = RestTest(args.url)
    client.test_hello_world()
