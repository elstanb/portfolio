import requests

base_url = 'http://127.0.0.1:8000/redfish/v1/'
base_accounts = base_url + 'AccountService/Accounts/'
base_privileges = base_url + 'AccountService/Roles/'

############## LIST USER ACCOUNTS ON HOST ##############

def list_users():
        x = requests.get(base_accounts)
        accounts =x.json().get('Members')
        # print(accounts)

        for a in accounts:
                print(requests.get(base_url + a['@odata.id']).json())
                list_of_accounts += requests.get(base_url + a['@odata.id']).json() + '\n'

        return list_of_accounts

###### REVOKE ALL PRIVILEGES FROM A USER ON A HOST ######

def revoke_privileges(id):
        # Provides user with lowest privilege possible: ReadOnly
        # Assigned Permissions to role: Login and ConfigureSelf
        data = '{"RoleId": "ReadOnly", "Links": {"Role": {"@odata.id": "/redfish/v1/AccountService/Roles/ReadOnly"}}}'
        r = requests.patch(base_accounts + str(id), data)

        print(r.status_code)
        print(requests.get(base_accounts + str(id)))
        return r.status_code

################ RESET A USER'S PASSWORD #################

def reset_user_password(id, new):
        data = '{"Password":"' + new + '"}'
        print(data)
        r = requests.patch(base_accounts + str(id), data)

        print(r.status_code)
        print(requests.get(base_accounts + str(id)))
        return r.status_code

#### MAIN ####
def main():
    #list_users()
    #revoke_privileges(2)
    reset_user_password(2, "itsme123")

if __name__ == "__main__":
    main()