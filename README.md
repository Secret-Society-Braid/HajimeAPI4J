![HajimeAPI4J](https://user-images.githubusercontent.com/56146205/143006456-41b668da-cdf7-40fa-934b-489189417ef3.png)

# HajimeAPI4J

[![Java Test with Gradle](https://github.com/Secret-Society-Braid/HajimeAPI4J/actions/workflows/gradle.yml/badge.svg)](https://github.com/Secret-Society-Braid/HajimeAPI4J/actions/workflows/gradle.yml)

[ふじわらはじめAPI](https://api.fujiwarahaji.me/doc/) のJavaWrapperです

## はじめに

このプロジェクトは参照元である「ふじわらはじめAPI」の開発者とは何ら関係なく、あくまでも個人での開発になります。

ですので、このラッパーライブラリに不具合などがあったとしても、「ふじわらはじめAPI」様の方へは連絡しないでください。

APIの開発者様へ連絡を取り、使用するユーザーエージェント、およびソースコードの公開場所をお伝えし、内容を明らかにしています。

## 対応バージョン

| APIバージョン |      対応する最新バージョン      | バグや不具合対応の可否 |
|:--------:|:---------------------:|:-----------:|
|    v1    | (v2.0.7-Experimental] |      ❎      |
|   v2.x   |  [v3.0.0, v5.0.x.x]   |      ✅      |
|    v3    | [v6.0.0.0-Alpha.1, )  |      ✅      |

__`v2.x` 系統を参照しているライブラリでは `v3` 系統を参照しているライブラリで行われたバグ修正、不具合修正のバックポートも行います。__

__また、`v1`系統を参照しているライブラリでは今後更新は行わない予定です。特別な事情等がない場合は `v2.x`系統のライブラリをご利用ください。__

## ライセンス

このプロジェクトはApache License 2.0の下で配布されています。

![Creative Commons BY-NC](https://licensebuttons.net/l/by-nc/4.0/88x31.png)

また、参照元であるAPIは[Creative Commons License BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) の下でのみ使用が許可されています。

## 寄付について

このプロジェクトでは寄付を歓迎しております。

これはこのOSSプロジェクトをフリーで使用できるようにするためであり、営利目的ではありません。寄付をするか、しないかは使用している皆様自身の意思になります。

## 依存ライブラリ

このプロジェクトでは以下のライブラリを使用しています。

- [Jackson-Core](https://github.com/FasterXML/jackson-core)
- [Jackson-Databind](https://github.com/FasterXML/jackson-databind)
- [Jackson-annotation](https://github.com/FasterXML/jackson-annotations)
- [Guava](https://github.com/google/guava)
- [OkHttp](https://square.github.io/okhttp/)
- [SLF4J](https://www.slf4j.org/)
- [Logback](https://logback.qos.ch/)
- [JUnit jupiter(テストライブラリ)](https://junit.org/junit5/)
