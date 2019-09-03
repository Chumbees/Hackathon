# Nivahb Hackathon Repo

Read this article to get React working. 
https://medium.com/swlh/how-to-setup-your-development-environment-using-react-native-on-windows-768a14ff8aec

When you try to run the program, it may give you an error about local.properties needing to know the location of your SDK. Create that file yourself in the android folder, all it needs is 

`sdk.dir=C:\\Users\\q1431716\\AppData\\Local\\Android\\Sdk`

You will also need to set up a virtual device or connect your own android phone if you have one. This article shows you how to set up an emulator in android studio.
https://developer.android.com/studio/run/managing-avds

# To run: 

set up env and virtual device

run emulator for device

npm start

In a new terminal: npm run android-windows (if you followed the article)

App should appear in emulator, right click emulator and hit ctrl+m, then enable live reload to see changes when saving in IDE