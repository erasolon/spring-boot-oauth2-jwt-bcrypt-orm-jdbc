##Oauth2 server using JWT token

This is a demo  for Oauth2 server using Json Web Token (JWT)

**To get token**
curl account-service:password@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=password

**To check  the provided token**
curl -u customer-service:password -X POST http://localhost:8080/oauth/check_token -H "Accept: application/json" -d "token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzQ4NzE1NTAsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZmQ0YWI1MmEtMjUwZC00YjZlLTllMmUtY2Y2N2NjNDQ2Yzg5IiwiY2xpZW50X2lkIjoiYWNjb3VudC1zZXJ2aWNlIiwic2NvcGUiOlsicmVhZCJdfQ.Bk8fk1tRPuOg6gdmCYLBjuR864aIGVU1eErOVOEjxC4"

**To access the resource using the Token**
curl http://localhost:8080/demo/user/ -H "Accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzQ4NzE1NTAsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZmQ0YWI1MmEtMjUwZC00YjZlLTllMmUtY2Y2N2NjNDQ2Yzg5IiwiY2xpZW50X2lkIjoiYWNjb3VudC1zZXJ2aWNlIiwic2NvcGUiOlsicmVhZCJdfQ.Bk8fk1tRPuOg6gdmCYLBjuR864aIGVU1eErOVOEjxC4"