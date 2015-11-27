#!/usr/bin/python
"""
@author xajkep
@date   20151127

Test the API with all apikey

Features tested:
    + leaderboard

"""

import requests
import MySQLdb
from termcolor import colored

URL = "http://localhost:8080/AMT_Projet_Untitled/api"

PATHS = [
    "/leaderboard/current"
]

DB_HOST = "localhost"
DB_USER = "amt"
DB_PASS = "amt"
DB_NAME = "amt"

apikeys = []

conn = MySQLdb.connect(DB_HOST, DB_USER, DB_PASS, DB_NAME)

with conn:
    cur = conn.cursor()
    cur.execute("SELECT * FROM APIKEY")
    rows = cur.fetchall()
    for r in rows:
        apikeys.append(r[1])

    for apikey in apikeys:
        print "[+] Testing with apikey: %s" % apikey
        for p in PATHS:
            print "[+] GET %s" % (URL+p)
            r = requests.get(URL+p, headers={'Authorization':apikey})
            print "%s [%s]\n" % (r.text, colored(r.status_code, 'blue'))
