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

@Table(name="favoriteboxes")
@NamedQueries({
    @NamedQuery(
            name = "getFavoriteboxesCount",     //対象の日報の番号だけ抽出し、いいねの合計をカウントする
            query = "SELECT COUNT(fb) FROM Favoriteboxes AS fb where fb.report=:report"
            ),

@NamedQuery(
            name = "getsomeFavoriteboxes",   //favoriteboxesの中の (select)その社員の (where)report のみ抽出
            query = "SELECT fb FROM Favoriteboxes AS fb where fb.report=:report and fb.employee=:employee"
            )
    })


@Entity
public class Favoriteboxes {
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

}