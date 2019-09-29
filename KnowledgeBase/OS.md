# Operating System

In this guide, we introduce some basic knowledge about operating systems (OS).

## Difference between process, thread and co-routine

- Process is a larger unit compared to thread. Thus, process is more expensive but certainly more independent.
	- Each process has its own virtual memory space.
- Thread is a smaller unit. Therefore, it could provide better concurrency.
	- Each thread has its own unique threadID, values in CPU registers, stack, error code, etc.
- Process is independent because each process gets an exclusive memory range. However, threads within the same process share the memory space.

## Difference between concurrency and parallelism

- Concurrency could happen on the same CPU. This is typically achieved by the process scheduler at the OS level. Context switch will be performed. However, note that tasks are actually not executed in parallel. This still saves time due to blocking operations, like I/O.
- Parallelism means multiple cores. Thus, tasks can definitely be executed in parallel.
