package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongAuthor implements Serializable {
	private Long songId;
	private Long authorId;

	@Override
	public int hashCode() {
		return Objects.hash(authorId, songId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongAuthor other = (SongAuthor) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(songId, other.songId);
	}

}
