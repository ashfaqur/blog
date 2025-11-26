FROM python:3.12-slim

WORKDIR /script

# Install dependencies
COPY requirements.txt .
RUN python -m pip install --no-cache-dir -r requirements.txt

# Run test
COPY rest_api_test.py .
CMD ["pytest", "--log-cli-level=INFO", "rest_api_test.py"]

