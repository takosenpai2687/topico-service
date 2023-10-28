import glob
import os

import requests
from config import ROOT_URL
from images import download_image, upload_image
from json_util import read_json

ROOT_FOLDER = '../resources/img'


def get_img_file(key, file_name):
    search_path = f"{ROOT_FOLDER}/{key}/{file_name}.*"
    files = glob.glob(search_path)
    if len(files) == 0:
        return None
    return files[0]


def create_communities(headers):
    url = ROOT_URL + '/api/v1/admin/communities'
    comms = read_json('../resources/mock/create_comms.json')
    for comm in comms:
        print(comm)
        img_root = f"../resources/img/{comm['key']}"
        download_image(comm['avatar_url'], img_root, 'avatar')
        download_image(comm['banner_url'], img_root, 'banner')
        # get avatar and banner image file paths
        avatar_img_path = get_img_file(comm['key'], 'avatar')
        banner_img_path = get_img_file(comm['key'], 'banner')
        print(avatar_img_path)
        print(banner_img_path)
        # upload images
        avatar_id = upload_image(avatar_img_path, headers)
        banner_id = upload_image(banner_img_path, headers)
        # create community
        data = {
            "name": comm['name'],
            "description": comm['description'],
            "avatar": avatar_id,
            "banner": banner_id
        }
        print(headers)
        res = requests.post(url, json=data, headers=headers, verify=False).json()
        print(res)
        print(
            f"{res['code']} {res['message']} ||| Create community: name={comm['name']}")
