package com.example.demo.composite;

import java.io.Serializable;
import java.util.Objects;

public class SongGenreId implements Serializable{
	private Long songId;
	private Long GenreId;
	@Override
	public int hashCode() {
		return Objects.hash(GenreId, songId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongGenreId other = (SongGenreId) obj;
		return Objects.equals(GenreId, other.GenreId) && Objects.equals(songId, other.songId);
	}
	
}
