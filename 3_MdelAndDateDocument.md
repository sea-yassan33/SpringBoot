## 1.エンティティクラスの作成
```
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Person {

	/*
	 * プライマリキーとして表示
	 * 値は自動生成
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@Column(length = 50, nullable = false)
	private String name;

    public long getId() {
		return id;
	}public void setId(long id) {
		this.id = id;
	}

}
```

## 2.リポジトリクラスの作成
- ripositoriesのパッケージを作成
- /src/main/java/com/example/demo/reqositories/のは以下にインターフェースを作成
```
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Person;

@Repository
public interface PearsonRepository extends JpaRepository<Person, Long>{

}
```

## 3.Create
- @ModelAttribute
  - エンティティクラスのインスタンスを自動的に用意

-　@Transactional
  - データベースを利用する一連の処理を一括して実行するための仕組み
  - データベースの変更を伴うような操作は@Transactionalをつける事が基本
  
## 4.Edit
- データのupdate機能
- 編集画面へ遷移
https://github.com/sea-yassan33/SpringBoot/commit/f2af5db53287e26ec2bd2ccd4bb4402d4dddf4ca

## 5.Delet
- データのdelete機能
https://github.com/sea-yassan33/SpringBoot/commit/162b211ba551af2dedc36ac25da9a68131a7e497


