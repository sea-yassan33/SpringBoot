# Thymeleafテンプレート

## build.gradleにインストール
```
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
```

## テンプレートに受け渡し
- タグ内にth:text="${msg}"を入れる
- th:tex；要素のテキストを指定
- ${msg}：変数
```
template例
<body class="container">
	<h1 class="display-4">Hello page</h1>
	<p th:text="${msg}"></p>
	<p class="h6 aleart alert-primary" th:text="${total}"></p>
</body>
```

## コントローラでModelを利用
```
import org.springframework.ui.Model;

@RequestMapping("/")
	public String index(Model model) {
		String mssageStr = "コントローラに用意したメッセージ!";
		model.addAttribute("msg",mssageStr);
		return "index";
	}
```

## パラメータを利用
-　パラメータ変数を用意して利用する
- 末尾に整数をつけてアクセスする
- http://localhost:8080/50
```
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/{num}")
	public String index(@PathVariable int num,Model model) {
		int res = 0;
		for(int i =1; i <= num; i++) {
			res += i;
		}
		String mssageStr =  "total: " + res;
		model.addAttribute("msg",mssageStr);
		return "index";
	}
```

## ModelAndViewクラスの利用
- 使用するテンプレートとそこで使われるデータを管理するModel
```
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/{num}")
	public ModelAndView index(@PathVariable int num,ModelAndView mav) {
		int res = 0;
		for(int i =1; i <= num; i++) {
			res += i;
		}
		String mssageStr =  "total: " + res;
		mav.addObject("msg",num + "までの合計を計算します。");
		mav.addObject("total",mssageStr);
		mav.setViewName("index");
		return mav;
	}
```

## フォームを利用
- templateの作成
```
<h1 class="display-4">Hello page</h1>
<p th:text="${msg}"></p>
<div class="h6 aleart alert-primary">
	<form method="post" action=".">
		<div class="input-group">
			<input type="text" name="text1" th:value="${value}" class="form-control me-1"/>
			<span class="input-group-btn">
				<input type="submit" value="Click" class="btn btn-primary px-4">
			</span>
		</div>
	</form>
</div>
```

- controllerの作成
```
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

String entry = "index";
	
@RequestMapping(value="/", method = RequestMethod.GET)
public ModelAndView index(ModelAndView mav) {
	String msgStr = "名前を書いて下さい";
	mav.addObject("msg",msgStr);
	mav.setViewName(entry);
	return mav;
}

@RequestMapping(value = "/",method = RequestMethod.POST)
public ModelAndView form(@RequestParam("text1") String str, ModelAndView mav) {
	mav.addObject("msg","こんにちは" + str + "さん！");
	mav.addObject("value", str);
	mav.setViewName(entry);
	return mav;
}

```

## フォームコントローラ
- template
```
<h1 class="display-4 mb-4">Hello page</h1>
<div th:if="${flg}">
	<ul th:each="resultMsg:${msg}">
		<li th:text="${resultMsg}"></li>
	</ul>
</div>
<div th:if ="${!flg}">
	<p th:text="${msg}"></p>
</div>
<div class="h6 aleart alert-primary">
	<form method="post" action=".">
		<div class="my-2">
			<input type="checkbox" name="check1" class="form-check-input" />
			<label for="check1" class="frm-check-label">チェック</label>
		</div>
		<div class="my-2">
			<input class="form-check-input" type="radio" name="radio1" value="male" />
			<label for="radioA" class="frm-check-label">男性</label>
		</div>
		<div class="my-2">
			<input class="form-check-input" type="radio" name="radio1" value="female" />
			<label for="radioB" class="frm-check-label">女性</label>
		</div>
		<div class="my-2">
			<select class="form-select" name="select1" size="1" >
				<option value="Windows">Windows</option>
				<option value="Mac">Mac</option>
				<option value="Linax">Linux</option>
			</select>
		</div>
		<div class="my-2">
			<select class="form-select" name="select2" size="4" multiple="multiple" >
				<option value="Android">Android</option>
				<option value="iPhone">iPhone</option>
				<option value="Winfone">Windows Phone</option>
			</select>
		</div>
		<input class="btn btn-primary" type="submit" value="Click" />
	</form>
</div>
```

- controller
```
String entry = "index";
Boolean flg = false;

@RequestMapping(value="/", method = RequestMethod.GET)
public ModelAndView index(ModelAndView mav) {
	flg = false;
	String msgStr = "フォームを送信してください。";
	mav.addObject("flg",flg);
	mav.addObject("msg",msgStr);
	mav.setViewName(entry);
	return mav;
}

@RequestMapping(value = "/",method = RequestMethod.POST)
public ModelAndView form(
		@RequestParam(value = "check1",required = false) boolean check1,
		@RequestParam(value = "radio1",required = false) String radio1,
		@RequestParam(value = "select1",required = false) String select1,
		@RequestParam(value = "select2",required = false) String[] select2,
		ModelAndView mav) {
	List<String> resList = new ArrayList<>();
	
	try {
		resList.add("check: " + check1);
		resList.add("radio: " + radio1);
		resList.add("select: " + select1);
	} catch (NullPointerException e) {}
	try {
		String select2Str = select2[0];
		for(int i=1; i < select2.length;i++) {
			select2Str += ", " + select2[i];
		}
		resList.add("select2:" + select2Str);
	} catch (NullPointerException e) {
		resList.add("null");
	}
	
	flg = true;
	mav.addObject("flg",flg);
	mav.addObject("msg",resList);
	mav.setViewName(entry);
	return mav;
}
```

