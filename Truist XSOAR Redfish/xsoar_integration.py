import requests
import urllib3

urllib3.disable_warnings()

def list_users(base_url):
    req = requests.get(base_url + 'AccountService/Accounts/')
    members = req.json().get('Members')
    list_of_accounts = []

    for member in members:
        list_of_accounts.append(requests.get(base_url + member['@odata.id']).json())
    return list_of_accounts

def revoke_privileges(user_id, base_url):
    data = """{
        "RoleId": "ReadOnly",
        "Links": {"Role": {"@odata.id": "/redfish/v1/AccountService/Roles/ReadOnly"}}
        }"""

    r = requests.patch(base_url + 'AccountService/Accounts/' + str(user_id), data)
    if r.ok:
        print("User account {} privileges revoked".format(user_id))
    else:
        print("ERROR! User account {} privileges not revoked".format(user_id))


def reset_user_password(user_id, new, base_url):
    data = '{"Password":"' + new + '"}'
    r = requests.patch(base_url + 'AccountService/Accounts/' + str(user_id), data)

    if r.ok:
        print("User account {} password reset".format(user_id))
    else:
        print("ERROR! User account {} password not reset".format(user_id))

def main():
    command = demisto.command()
    args = demisto.args()
    params = demisto.params()
    base_url = params.get("url")

    if command == "test-module":
        result = "not ok"
        req = requests.get(base_url)
        if req.status_code == 200:
            result = "ok"
        demisto.results(result)
    elif command == "list-users":
        output = list_users(base_url)
        demisto.results(
            {
                "Type": entryTypes["note"],
                # "HumanReadable": f"{len(ordered_cif_results)} Results:\n{human_readable}",
                "Contents": output,
                "ContentsFormat": formats["json"],
                "EntryContext": {"redfish_list_users": output},
            }
        )
    elif command == "revoke-privileges":
        user_id = args.get("user_id")
        revoke_privileges(user_id, base_url)
    elif command == "reset-user-password":
        user_id = args.get("user_id")
        new_password = args.get("new_password")
        reset_user_password(user_id, new_password, base_url)

if __name__ in ['__main__', '__builtin__', 'builtins']:
    main()