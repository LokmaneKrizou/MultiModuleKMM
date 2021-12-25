object Modules {
    const val kmm =":kmm"
    const val core= ":kmm:core"
    const val feature=":feature"
    const val android =":android"
    const val app ="$android:app"
    const val kmm_feature= "$kmm:$feature"
    const val constants= "$kmm:constants"
    const val shared= "$kmm:shared"
    const val android_feature="$android:$feature"
    const val feature_datasource="$kmm_feature:feature-datasource"

}