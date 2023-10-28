import requests
from urllib3 import encode_multipart_formdata
from config import ROOT_URL
import os
import glob


def upload_image(file_path, headers):
    url = ROOT_URL + '/api/v1/images'
    file_path = file_path.replace('\\', '/')
    ext = file_path.split('.')[-1]
    with open(file_path, 'rb') as file_binary:
        files = {'image': (file_path, file_binary.read())}
        body, content_type = encode_multipart_formdata(files)
        res = requests.post(url, data=body, verify=False, headers={
            'Authorization': headers['Authorization'],
            'Content-Type': content_type}
                            ).json()
        print(res)
        print(
            f"{res['code']} {res['message']} ||| Upload image: file_path={file_path}")
        img_id = res['data']['id']
        return img_id


def download_image(url, folder, file_name):
    # mkdir
    if not os.path.exists(folder):
        os.makedirs(folder)

    # check existing
    existing_files = glob.glob(os.path.join(folder, f"{file_name}.*"))
    if existing_files:
        print(f"File '{folder}/{file_name}' already exists. Skipping download.")
        return

    # make request
    res = requests.get(url, verify=False)
    content_type = res.headers['content-type']
    ext = content_type.split('/')[-1]
    file_path = f"{folder}/{file_name}.{ext}"

    # download
    with open(file_path, 'wb') as f:
        f.write(res.content)
    print(f"Download image: url={url} file_path={file_path}")
