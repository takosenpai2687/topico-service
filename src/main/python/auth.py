import requests
from config import ROOT_URL

first_names = [
    "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", "William", "Elizabeth",
    "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen",
    "Christopher", "Nancy", "Daniel", "Lisa", "Matthew", "Betty", "Anthony", "Margaret", "Mark", "Sandra",
    "Donald", "Ashley", "Steven", "Kimberly", "Paul", "Donna", "Andrew", "Emily", "Joshua", "Carol",
    "Kenneth", "Michelle", "Kevin", "Amanda", "Brian", "Melissa", "George", "Deborah", "Edward", "Stephanie",
    "Ronald", "Rebecca", "Timothy", "Laura", "Jason", "Helen", "Jeffrey", "Sharon", "Ryan", "Cynthia",
    "Jacob", "Kathleen", "Gary", "Amy", "Nicholas", "Shirley", "Eric", "Angela", "Stephen", "Anna",
    "Jonathan", "Ruth", "Larry", "Brenda", "Justin", "Pamela", "Scott", "Nicole", "Brandon", "Katherine",
    "Frank", "Samantha", "Benjamin", "Christine", "Gregory", "Catherine", "Raymond", "Virginia", "Samuel", "Debra",
    "Patrick", "Rachel", "Alexander", "Janet", "Jack", "Emma", "Dennis", "Carolyn", "Jerry", "Maria",
]


def create_users():
    for i in range(len(first_names)):
        name = first_names[i]
        email = "test" + str(i) + "@qq.com"
        body = {"email": email, "password": "123456",
                "nickName": name, "confirmPassword": "123456"}
        res = requests.post(
            ROOT_URL + "/api/v1/auth/signup", json=body, verify=False).json()
        print(
            f"{res['code']} {res['message']} ||| Sign up user: email={email}, nickName={name}")


def admin_login():
    url = ROOT_URL + '/api/v1/auth/login'
    data = {'email': 'admin@qq.com', 'password': '123456'}
    response = requests.post(url, json=data, verify=False)
    token = response.json()['data']['token']
    headers = {'Authorization': 'Bearer ' + token}
    return headers
