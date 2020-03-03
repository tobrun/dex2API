Kotlin written CLI tool to extract public API methods from an `classes.dex` file.

### How to
This project comes with an example implemented, to extract public API from your own APK.
- replace `classes.dex` file in the root of this project
- adjust configuration in `src/main/resources/config.resources` to match the `classes.dex`
- run `./gradlew run` and validate the output

