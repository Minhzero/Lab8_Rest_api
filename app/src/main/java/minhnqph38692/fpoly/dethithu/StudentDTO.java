package minhnqph38692.fpoly.dethithu;

public class StudentDTO {
    String _id,ho_ten_ph38692,que_quan_ph38692,diem_ph38692,hinh_anh_ph38692;
    private String createAt;
    private String updateAt;

    public StudentDTO(String _id, String ho_ten_ph38692, String que_quan_ph38692, String diem_ph38692, String hinh_anh_ph38692, String createAt, String updateAt) {
        this._id = _id;
        this.ho_ten_ph38692 = ho_ten_ph38692;
        this.que_quan_ph38692 = que_quan_ph38692;
        this.diem_ph38692 = diem_ph38692;
        this.hinh_anh_ph38692 = hinh_anh_ph38692;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public StudentDTO() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getHo_ten_ph38692() {
        return ho_ten_ph38692;
    }

    public void setHo_ten_ph38692(String ho_ten_ph38692) {
        this.ho_ten_ph38692 = ho_ten_ph38692;
    }

    public String getQue_quan_ph38692() {
        return que_quan_ph38692;
    }

    public void setQue_quan_ph38692(String que_quan_ph38692) {
        this.que_quan_ph38692 = que_quan_ph38692;
    }

    public String getDiem_ph38692() {
        return diem_ph38692;
    }

    public void setDiem_ph38692(String diem_ph38692) {
        this.diem_ph38692 = diem_ph38692;
    }

    public String getHinh_anh_ph38692() {
        return hinh_anh_ph38692;
    }

    public void setHinh_anh_ph38692(String hinh_anh_ph38692) {
        this.hinh_anh_ph38692 = hinh_anh_ph38692;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
