# MultiPartProgressbar

A progressbar which contains different parts of progress.

<img src="./preview/preview.gif" alt="preview" title="preview" width="300" height="300" align="right"/>

## Download
![](https://img.shields.io/bintray/v/pklotcorp/Library/multi-part-progressbar?label=version) 
```groovy
implementation 'com.pklotcorp:multi-part-progressbar:$version'
```

## XML
```xml
<com.pklotcorp.multipartprogressbar.MultiPartProgressbar
    android:layout_width="300dp"
    android:layout_height="300dp"
    app:icon_radius="20dp"
    app:icon_resource="@drawable/shape_progressbar_icon"
    app:progress_width="10dp" />
```

## Attributes
|Attribute  | Example  |
|---|---|
| icon_resource  |  @drawable/shape_progressbar_icon |
| icon_radius  | 20dp  |
| progress_width  | 10dp  |

## Usage
### Setup progress parts
Provide a list of `ProgressPart` for `MultiPartProgressbar` via `setupProgressParts(List<ProgressPart>)`:
```kotlin
multiPartProgressbar.setupProgressParts(
            listOf(
                object : ProgressPart() {
                    override fun startColor() = Color.MAGENTA
                    override fun endColor() = Color.RED
                    override fun minValue() = 0
                    override fun maxValue() = 30
                },
                object : ProgressPart() {
                    override fun startColor() = Color.CYAN
                    override fun endColor() = Color.BLUE
                    override fun minValue() = 30
                    override fun maxValue() = 70
                },
                object : ProgressPart() {
                    override fun startColor() = Color.YELLOW
                    override fun endColor() = Color.GREEN
                    override fun minValue() = 70
                    override fun maxValue() = 120
                },
            )
        )
```
### Set progress
Assign progress(0-1) to `MultiPartProgressbar`:
```kotlin
multiPartProgressbar.setProgress(0.5f)
```

## License
```
Copyright 2020 PKLOTCORP

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
