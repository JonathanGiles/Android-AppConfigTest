# Android-AppConfigTest

**Current status:** Tested and works on Android API level 24, 25, and 26. App crashes on startup on 23 as it can't find the `MainActivity` class. It fails to start on level 21 due to 'stale dexed jars' / 'dexopt error'.

To run, create an App Config instance in Azure, and simply edit the `connectionString` variable in `MainActivity` to refer to it.
