package com.example.demo.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongSingerId implements Serializable {
	private Long songId;
	private Long SingerId;
	@Override
	public int hashCode() {
		return Objects.hash(SingerId, songId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongSingerId other = (SongSingerId) obj;
		return Objects.equals(SingerId, other.SingerId) && Objects.equals(songId, other.songId);
	}
	
}
