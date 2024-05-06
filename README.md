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


## JpaRepositoryメソッドについて
- Repositoryインタフェースを定義
- JPQLというSQLを簡易化したような言語機能が内臓されている
- メソッド名を基にDB処理内容を自動生成
https://dev.classmethod.jp/articles/use_spring-boot-jpa-jpql/

|メソッド名の作成例|実行されるJPQL|
|:----|:----|
|findById(String id)|FROM mydata WHERE id = '引数id'|
|findByIdNot(String id)|FROM mydata WHERE id <> '引数id'|
|findByIdAndTitle(String id, String title)|FROM mydata WHERE id = '引数id' and title = '引数title'|
|findByIdOrTitle(String id, String title)|FROM mydata WHERE id = '引数id' or title = '引数title'|
|findByIdBetween(String start, String end)|FROM mydata WHERE id BETWEEN '引数start' and '引数end'|
|findByIdLessThan(String id)|FROM mydata WHERE id < '引数id'|
|findByIdGreaterThan(String id)|FROM mydata WHERE id > '引数id'|
|findByCountIsNull()|FROM mydata WHERE count IS NULL|
|findByCountIsNotNull()|FROM mydata WHERE count IS NOT NULL|
|findByTitleLike(String word)|FROM mydata WHERE title LIKE '引数word'|
|（ワイルドカードは引数に渡す時点で書いておく必要が有ります。）|
|findByTitleNotLike(String word)|FROM mydata WHERE title LIKE '引数word'|
|findByIdOrderByTitleAsc(String id)|FROM mydata WHERE id = '引数id' ORDER BY title ASC|
|findByIdOrderByIdDesc(String id)|FROM mydata WHERE id = '引数id' ORDER BY id DESC|
|findByIdIn(String[] ids)|FROM mydata WHERE id IN '引数に配列ids'|
|findByIdNotIn(ArrayList ids)|FROM mydata WHERE id NOT IN '引数にコレクションids'|

## バリデーションについて
- パッケージの追加
```
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

## EntityManagerについて
- エンティティを操作するための機能
- DAOインターフェイスを作成
- DAOインターフェイスを継承してDtoクラスを作成

## JPQLによるデータ操作
- データベースを操作するための簡易言語

## Criteria APIによるデータ操作
- Java用のデータベース管理

## データベース操作のパフォーマンス比較
- 10万件のDBに対して、ランダムな1件を引いてくることを1000回ずつ実行
- その実行を５回ずつ繰り返す。
- 実行平均（合計実行時間 / 5）を算出。
- Native Queryを基準にCriteria APIは２倍、JPQLは3倍実行に時間がかかる。

|クエリ|時間（ミリ秒）|
|:----|:----|
|Native Query|421|
|JPQL|1232|
|Criteria API|804|
