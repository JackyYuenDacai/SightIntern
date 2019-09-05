# Errors

These are the errors that may occur and the error code return in data received JSON package.

Error Code | Meaning
---------- | -------
0 | Success
101 | Privilege error
202 | Parameter error
303 | Invalid
401 | Token expired
502 | Failed
900 | Undetermined error

## 101 Privilege error

Occur when lower/equal privilege user request for edit/view information of equal/higher privilege level user.

When privilege level are equal, user must have parental relationship towards another user to view another user's information.

Below is the table of privilege level of different roles, lower the privilege level number means higher privilege.

Privilege Level | Role
---------- | -------
0 | Administrator
1 | Teacher
2 | Staff
3 | Parents
4 | Student
5 | Point

## 202 Parameter error

Occur when lack of parameters needed for the request to proceed.
