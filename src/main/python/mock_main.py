import requests
import json
from auth import create_users, admin_login
from comms import create_communities

HEADERS = {}


def main():
    create_users()
    HEADERS = admin_login()
    create_communities(HEADERS)

if __name__ == '__main__':
    main()

# python ./mock_main.py
