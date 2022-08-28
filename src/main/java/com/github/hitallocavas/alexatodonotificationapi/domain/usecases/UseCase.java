package com.github.hitallocavas.alexatodonotificationapi.domain.usecases;

public interface UseCase<I, C, O> {
    O execute(I input, C context);
}
