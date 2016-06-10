package com.example.dkkbg_000.nytretrofit.model;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @Expose
    private String thumbnailStandard;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("updated_date")

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     *
     * @return
     * The thumbnailStandard
     */
    public String getThumbnailStandard() {
        return thumbnailStandard;
    }

    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
    }

}