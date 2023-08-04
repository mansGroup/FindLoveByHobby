package com.fin.love.repository.image;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "HOBBYPICTURE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class HobbyPicture {
	
	@Id
	private String id;
	
	@Column
	private String hobbyPic1;
	
	@Column
	private String hobbyPic2;
	
	@Column
	private String hobbyPic3;
	
	public HobbyPicture hobbyPic1Update(String path) {
        this.hobbyPic1 = path;
        
        return this;
    }
	
	public HobbyPicture hobbyPic2Update(String path) {
        this.hobbyPic2 = path;
        
        return this;
    }
	
	public HobbyPicture hobbyPic3Update(String path) {
        this.hobbyPic3 = path;
        
        return this;
    }
	
}
