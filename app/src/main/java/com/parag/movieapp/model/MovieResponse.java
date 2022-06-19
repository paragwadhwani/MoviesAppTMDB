package com.parag.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

@SerializedName("average_rating")
@Expose
private Double averageRating;
@SerializedName("backdrop_path")
@Expose
private String backdropPath;
@SerializedName("description")
@Expose
private String description;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("page")
@Expose
private Integer page;
@SerializedName("poster_path")
@Expose
private String posterPath;
@SerializedName("public")
@Expose
private Boolean _public;
@SerializedName("results")
@Expose
private List<Movie> results = null;
@SerializedName("revenue")
@Expose
private Long revenue;
@SerializedName("runtime")
@Expose
private Integer runtime;
@SerializedName("sort_by")
@Expose
private String sortBy;
@SerializedName("total_pages")
@Expose
private Integer totalPages;
@SerializedName("total_results")
@Expose
private Integer totalResults;

public Double getAverageRating() {
return averageRating;
}

public void setAverageRating(Double averageRating) {
this.averageRating = averageRating;
}

public String getBackdropPath() {
return backdropPath;
}

public void setBackdropPath(String backdropPath) {
this.backdropPath = backdropPath;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
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

public Integer getPage() {
return page;
}

public void setPage(Integer page) {
this.page = page;
}

public String getPosterPath() {
return posterPath;
}

public void setPosterPath(String posterPath) {
this.posterPath = posterPath;
}

public Boolean getPublic() {
return _public;
}

public void setPublic(Boolean _public) {
this._public = _public;
}

public List<Movie> getResults() {
return results;
}

public void setResults(List<Movie> results) {
this.results = results;
}

public Long getRevenue() {
return revenue;
}

public void setRevenue(Long revenue) {
this.revenue = revenue;
}

public Integer getRuntime() {
return runtime;
}

public void setRuntime(Integer runtime) {
this.runtime = runtime;
}

public String getSortBy() {
return sortBy;
}

public void setSortBy(String sortBy) {
this.sortBy = sortBy;
}

public Integer getTotalPages() {
return totalPages;
}

public void setTotalPages(Integer totalPages) {
this.totalPages = totalPages;
}

public Integer getTotalResults() {
return totalResults;
}

public void setTotalResults(Integer totalResults) {
this.totalResults = totalResults;
}

    @Override
    public String toString() {
        return "MovieResponse{" +
                "averageRating=" + averageRating +
                ", backdropPath='" + backdropPath + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", page=" + page +
                ", posterPath='" + posterPath + '\'' +
                ", _public=" + _public +
                ", results=" + results +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", sortBy='" + sortBy + '\'' +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}