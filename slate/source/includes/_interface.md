# Web interfaces
This part will include all interfaces that are going to be used in web app and mobile app.

All the request parameters and data recevied json are wrapped in the format mentioned in [Request](#request).

Exclude the interfaces related to resources, which they need id and token noted in cookies.

##/api/submit
> request parameters

```json
{
	"record_token": Master token of that record,
	"form": stringyfied form data
}
```

> data received

```json
{

}
```

This interface is to submit filled form and form a complete record.

Each submit requires the user to have the master record token of that set of record before submitting.

Data submitted to database is in string form instead of JSON.

##/api/records
> request parameters

```json
{
	"location": Location filter,
	"name": Name filter,
	"role": Role filter,
	"status": Status filter done//ongoing,
	"registered": If the tag registered true//false,
	"time_start": “YYYY-mm-dd hh:MM:ss”,
	"time_end": “YYYY-mm-dd hh:MM:ss”
}
```

> data received

```json
{[
	{
		"id": user id,
		"name": users’ name,
		"tag_id": ,
		"time_start": ,
		"time_end": ,
		"duration": ,
		"record_token": Master token of that record
	},{…},{…},{…}
]}
```
This interface handles all the get request for records, and the parameters in request acts as filter.

##/api/link_google
> request parameters

```json
{

}
```

> data received

```json
{

}
```
This interface handles all the request related to linking account between google account.

##/api/user_config
> request parameters

```json
{
	"type": add/edit,
	"soc": Institution id,
	"id": users’ id,
	"name": users’ name,
	"role": users’ role,
	"icon_id": id of the icon file,
	"pwd": MD5 encoded password,
	"extra": extra description
}
```

> data received

```json
{
	"soc": Institution id,
	"id": users’ id,
	"name": users’ name,
	"role": users’ role,
	"icon_id": id of the icon file,
	"extra": extra description
}
```
This interface handles all the request related to edit or add user information.

##/api/tag_config
> request parameters

```json
{
	"type": add/del/query,
	"soc": Institution id,
	"id": users’ id,
	"role": users’ role,
	"tag_id": tags' id,
	"tag_type": Tag type,
}
```

> data received

```json
if query
{
	"tags":[
		{
			"tag_type": Tag's type,
			"tag_id": Tag's Id
		},
		{...},{...}]
}
```
This interface handles all the request related to edit or add tag information.

When query, the interface will return all tags' info related to that user id.

##/api/token_update
> request parameters

```json
{

}
```

> data received

```json
{
	"token":updated token
}
```
This interface updates token and rent token for another 24 hours.

##/api/upload
> request parameters

```json
{
}
```

> data received

```json
{
    "file_id": Id of the file assigned by server
}
```
POST method

This interface upload files.

Directly upload file through conventional upload method

Parameters Needed: id token

Return file_id if successful

Sample upload link:

http://localhost:8888/api/upload?id=Test&token=wi3ij34mo5

##/api/resource
> request parameters

```json
https://localhost/api/resource?file_id=XXXX
```

> data received

```json
receive raw file data.
```

This interface retreat files from server

And can directly used the link as resource in html.

Parameters Needed: id token file_id

Return file content if successful.
##/api/login
> request parameters

```json
{
	"id": Users' Id,
	"soc": Users' institution,
	"pwd": MD5 encoded password
}
```

> data received

```json
{
	"token": Access Token
}
```

This interface handles login requests.

Will return access token when id, soc, and pwd matches.


##/api/form_config
> request parameters

```json
{
	"type":add/del/change/query/list,
	"id":Id of the form,
	"soc":Institution code where the form belongs to,
	"form": stringyfied form data when need to add/change the form data stored
}
```

> data received

```json
When query for one form with Id and Soc
{
	"id":Id of the form,
	"soc":Institution code where the form belongs to,
	"form":  stringyfied form data
}

When list for multiple forms using Soc
{
	"forms":[{
		"id":Id of the form,
		"soc":Institution code where the form belongs to,
		"form":  stringyfied form data
		},{...},{...}]
}
```

When in need of query for all the form owned by one soc, uses type list.

When in need of query for one form using soc and id, uses type query.

##/api/search_user
> request parameters

```json
{
	"type":"id"/"name" search by id or name  
	"id": if search by id
	"name" if search by name
}
```

> data received

```json
{
	[{"id":...,
		"name":...,
		"extra":...,
		"role":...,
		"icon_id":...
		},{...},{...}]
}
```

When in need of query user by name, uses type name;

When in need of query user by id uses type id;
