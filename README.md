# About

A Kotlin class of store user data in simple tab separated value.

It's completed on 2023.05.15, 1 year 5 month 5 day ago. I almost forgot it,
 but it doesn't deserve to be forgotten.


# Usage

Read the key "age" with default value "0", return string.

```kotlin
class MainActivity : AppCompatActivity() {
  
  private val tc = TinyConf(this, "MAIN.tsv")
  
  private var confAge = 0

  override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

        tc.loadConf()
        confAge = tc.r("age", "0").toInt()

  }
}
```

Save "age".

```kotlin
tc.w("age", "28")
```
