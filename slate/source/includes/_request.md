# Request

All request towards web interfaces and received data must follow the same format shown here.

Unsuccess request will return with error code and maybe error message.

>Request json structure

```json
{
	"token": login token,
	"id": Account id,
	"soc": institution id,
	"parameters": {
	}
}
```
>Data recevied json structure

```json
{
	"error": Error code,
	"error_msg": Error message,
	"data": {
	}
}
```