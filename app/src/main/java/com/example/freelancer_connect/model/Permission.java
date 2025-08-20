package com.example.freelancer_connect.model;
public class Permission {
    private String label;
    private boolean checked;

    public Permission(String label, boolean checked) {
        this.label = label;
        this.checked = checked;
    }

    public String getLabel() { return label; }
    public boolean isChecked() { return checked; }
    public void setChecked(boolean checked) { this.checked = checked; }
}
