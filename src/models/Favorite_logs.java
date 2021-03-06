package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="favorite_logs")
@NamedQueries({
    @NamedQuery(
            name = "getFavorite_logsCount",
            query = "SELECT COUNT(f) FROM Favorite_logs AS f"
            //このままだとテーブル(いいねログ)の全てのレコードを抽出してしまうので、条件を付けて
            //「今ログインしている人」と「いいねをしようとした記事」だけ抽出するようにする
            )
    })

@Entity
public class Favorite_logs {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="employee_id",nullable=false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="report_id",nullable=false)
    private Report report;

    @Column(name="favorite_flag")
    private Integer favorite_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Integer getFavorite_flag() {
        return favorite_flag;
    }

    public void setFavorite_flag(Integer favorite_flag) {
        this.favorite_flag = favorite_flag;
    }


}