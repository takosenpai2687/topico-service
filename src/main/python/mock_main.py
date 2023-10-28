import requests
from auth import create_users, admin_login
from comms import create_communities

HEADERS = {}


def main():
    requests.packages.urllib3.disable_warnings(requests.packages.urllib3.exceptions.InsecureRequestWarning)
    create_users()
    HEADERS = admin_login()
    create_communities(HEADERS)


if __name__ == '__main__':
    main()

# python ./mock_main.py
