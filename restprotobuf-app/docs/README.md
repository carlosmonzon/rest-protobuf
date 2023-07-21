# rest-protobuf

## Run server

Go to *backend* section for instructions on how to run the backend

## Android app

Make sure you run it in a simulator only as the server is running locally.
Android emulator uses: `"http://10.0.2.2:8080/"`

## iOS app

Make sure you run it in a simulator only as the server is running locally.
iOS simulator uses: `"http://localhost:8080/"`

### Update DataKit XCFramework into iOS app

*Release XCFramework*
`./gradlew :shared:copyReleaseXCFramework`

*Debug XCFramework*
`./gradlew :shared:copyDebugXCFramework`
