#!/usr/bin/python
"""
@author xajkep
@date   20151127

Test the API with all apikey

Features tested:
    + leaderboard
    + getPointAwards & getPointAward
    + getBadge & getBadges

Usage: python api-request.py [-v | --verbose]
"""

import requests
import MySQLdb
import json
import sys
from termcolor import colored

URL = "http://localhost:8080/AMT_Projet_Untitled/api"

PATHS = [
    "/leaderboard/current",
    "/pointawards",
    "/badges"
]

DB_HOST = "localhost"
DB_USER = "amt"
DB_PASS = "amt"
DB_NAME = "amt"

VERBOSE = len(sys.argv) == 2 and sys.argv[1] in ['-v', '--verbose']
ERROR_COUNTER = 0

def verbose(s):
    if VERBOSE:
        print "[%s] %s" % (colored('v', "magenta"), colored(s, "white"))

def error(s):
    ERROR_COUNTER += 1
    print "[%s] %s" % (colored('e', "red"), colored(s, "red"))

def req(url):
    return requests.get(url, headers={'Authorization':apikey})

def checkSingle(href, jsonEntity):
    r = req(href)
    content = r.text
    verbose("GET "+ j['href'] + " ["+str(r.status_code)+"]")
    verbose(content)
    return r.status_code == 200 and json.loads(content) == jsonEntity


apikeys = []

conn = MySQLdb.connect(DB_HOST, DB_USER, DB_PASS, DB_NAME)

with conn:
    cur = conn.cursor()
    cur.execute("SELECT * FROM APIKEY")
    rows = cur.fetchall()
    for r in rows:
        apikeys.append(r[1])

    apikeys.append("FAKE-API-KEY")

    for apikey in apikeys:
        print "[+] Testing with apikey: %s" % apikey
        for p in PATHS:
            print "[+] GET %s" % ('/api'+p)
            r = req(URL+p)
            jsonResponse = json.loads(r.text)
            verbose(jsonResponse)

            print "[%s] %i result(s)\n" % (colored(r.status_code, 'blue'), len(jsonResponse))

            if r.status_code == 200:
                if len(jsonResponse) > 0 and 'href' in jsonResponse[0].keys():
                    print "[+] Reach all href link"

                    for j in jsonResponse:

                        if (checkSingle(j['href'], j)):
                            verbose(j['href']+ " [MATCH]")
                        else:
                            error(j['href']+ " [DOESNT MATCH]")
            else:
                print jsonResponse


    print "\n[+] %i features tested with %i different apikeys " % (len(PATHS), len(apikeys))
    print " | %i error(s) during the tests" % ERROR_COUNTER
