from config import ROOT_URL
import requests
import threading
import time
from auth import create_users, admin_login



def create_posts(headers, community_id):
    url = ROOT_URL + '/api/v1/posts'
    i = 0
    while i < 13:
        data = {
            "communityId": community_id,
            "title": 'title' + str(i),
            "content": 'content' + str(i),
            "tags": '[]',
            "spoiler": False
        }
        #print(headers)
        res = requests.post(url, json=data, headers=headers, verify=False).json()
        #print(res)
        i = i + 1


def main():
    requests.packages.urllib3.disable_warnings(requests.packages.urllib3.exceptions.InsecureRequestWarning)
    HEADERS = admin_login()
    thread_arr = []
    i = 1
    start_time = time.time()
    while i <= 13:
        thread = threading.Thread(target=create_posts,args=(HEADERS, 1))
        thread_arr.append(thread)
        thread.start()
        i = i + 1
    for thread in thread_arr:
        thread.join()
    print('total time:' + str(time.time() - start_time))

if __name__ == '__main__':
    main()
