FROM python:3.12-slim

WORKDIR /script

# Install dependencies
COPY requirements.txt .
RUN python -m pip install --no-cache-dir -r requirements.txt

# Run test
COPY rest_test.py .
CMD ["pytest", "rest_test.py"]

