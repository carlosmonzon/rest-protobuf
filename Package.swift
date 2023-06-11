// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/carlosmonzon/rest-protobuf/rest-protobuf/shared-kmmbridge/0.1.1/shared-kmmbridge-0.1.1.zip"
let remoteKotlinChecksum = "ca593907d57097357a07173441f302bca77a8c5f3f2ecb74427ea236a0cbf5c3"
let packageName = "DataKit"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)