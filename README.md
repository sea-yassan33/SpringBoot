# SpringBootプロジェクト立ち上げ

## 1.プロジェクト作成 
- 新規 > その他
- Spring Boot > Springスターター・プロジェクト」を選択して、「次へ」
- テンプレート・エンジンを「Thymeleaf」、Webを「Spring Web」にそれぞれチェックを入れ、完了を押下でプロジェクト作成完了

## 2.ビルドファイル
- リフレッシュ方法

## 3.HTMLファイルの作成
- templete > 右クリック > その他

## 4.コントロールの作成
- src/main/java/com/example/＊＊＊/の配下にcontrollerを作成

## 5.プロジェクトの実行
- プロジェクトを右クリックして、「実行 > Spring Boot アプリケーション」を選択
- 「http://localhost:8080/」にアクセス

## 6.CSS、JSファイルの配置
- src/main/resources/static/下にcssやjsといった形で専用フォルダなどを作る
- index.htmlでそれらのファイルを読み込
```
<link th:href="@{/css/index.css}" rel="stylesheet">
<script th:src="@{/js/index.js}"></script> 
```

## 7.Tymeleafの使い方
- [Document](./2_ThymeleafDocument.md)

## 8.モデルとデータベース
- [Document](./3_MdelAndDateDocument.md)