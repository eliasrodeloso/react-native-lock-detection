
# react-native-lock-detection

## Getting started

`$ npm install react-native-lock-detection --save`

### Mostly automatic installation

`$ react-native link react-native-lock-detection`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-lock-detection` and add `RNLockDetection.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNLockDetection.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.talosdigital.reactnative.RNLockDetectionPackage;` to the imports at the top of the file
  - Add `new RNLockDetectionPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-lock-detection'
  	project(':react-native-lock-detection').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-lock-detection/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-lock-detection')
  	```


## Usage
```javascript
import RNLockDetection from 'react-native-lock-detection';

// TODO: What to do with the module?
RNLockDetection;
```
  