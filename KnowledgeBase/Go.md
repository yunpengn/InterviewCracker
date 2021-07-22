# Go

In this guide, we introduce some knowledge about Go.

## Goroutines

- Go is a message-oriented programming language.
- A **goroutine** is a lightweight thread of execution. It is completely handled by the Go runtime, and does not incur any system-level context switch, etc.
- The scheduler in Go is based on the CSP (communicating sequential process) model. However, it has been improved to become the GMP (goroutine, machine and processor) model.
    - Each machine corresponds to an actual thread in the system kernel.
    - Each machine will be binded with a processor _(thus 1-to-1 relationship mapping)_, with the context needed to process user-level logical code.
    - Each processor is connected to multiple goroutines, one running while the others waiting.
- Communication between different goroutines happens via channels. Channels can also be used for synchronization.
    - `select` is like the channel-based version of `switch`. It can be used to wait on multiple channels.
        - `select` can be used together with `time.After` to implement a timeout functionality with a fallback function.
    - However, if you need to wait for the completion of all `n` channels, you probably want to use a `WaitGroup` (i.e., implemented via a mutex).
- Each processor has a `schedule` function that schedules goroutines from different queues:
    - A global queue across all processors;
    - Its local queue;
    - The local queue from other processors.

## `defer` keyword

- `defer` is a keyword in Go that can be used to execute something when the surrounding function returns.
- It is useful to use `defer` to close resources such as opened files, channels, etc.
- If there are multiple `defer` statements, they will be executed in the reverse order (i.e., first in last out) when the surrounding function returns.
