package cn.tommyyang.tools.httptool.model;

/**
 * Created by TommyYang on 2018/2/11.
 */
public class Repo {

    private Integer id;

    private String name;

    private String full_name;

    private Boolean html_url;

    private String description;

    private String url;

    public Repo(Integer id, String name, String full_name, Boolean html_url, String description, String url) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
        this.description = description;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Boolean getHtml_url() {
        return html_url;
    }

    public void setHtml_url(Boolean html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", html_url=" + html_url +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
