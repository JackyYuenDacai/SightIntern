# Web interfaces
This part will include all interfaces that are going to be used in web app and mobile app.

All the request parameters and data recevied json are wrapped in the format mentioned in [Request](#request).

##/api/submit
> request parameters

```json
{
	"record_token": Master token of that record,
	"form": {
		"Q_KEY0": A_KEY0,
		"Q_KEY1": A_KEY1,
		…
	}
}
```

> data received

```json
{

}
```

This interface is to submit filled form and form a complete record.

Each submit requires the user to have the master record token of that set of record before submitting.

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

##/api/update_token
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
This interface updates token and rent token for another 24 hours.

##/api/upload
> request parameters

```json
{
    "file_type": img/raw/...,
    "file_data": Stringifyed file data,
    "id": users' id,
}
```

> data received

```json
{
    "file_id": Id of the file assigned by server
}
```
This interface upload files toward user.

Files that are going to be upload must first stringifyed and pass through json towards interface.

