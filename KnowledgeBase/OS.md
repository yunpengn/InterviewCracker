# Operating System

In this guide, we introduce some basic knowledge about operating systems (OS).

## Difference between process, thread and co-routine

- Process is a larger unit compared to thread. Thus, process is more expensive but certainly more independent.
	- Each process has its own virtual memory space.
- Thread is a smaller unit. Therefore, it could provide better concurrency.
	- Each thread has its own unique threadID, values in CPU registers, stack, error code, etc.
- Process is independent because each process gets an exclusive memory range. However, threads within the same process share the memory space.

## Why do we need thread pool?

- A thread pool maintains a defined set of threads that could potentially be used to execute different tasks.
- This brings the following advantages:
	- Efficient since the same set of threads are reused again and again (so that no need to keep creating / destorying threads).
	- Explicitly control the number of concurrent requests / tasks (so that the system would not be overloaded).
	- Perform management on the defined set of threads.

## Difference between concurrency and parallelism

- Concurrency could happen on the same CPU. This is typically achieved by the process scheduler at the OS level. Context switch will be performed. However, note that tasks are actually not executed in parallel. This still saves time due to blocking operations, like I/O.
- Parallelism means multiple cores. Thus, tasks can definitely be executed in parallel.


## Can you explain the output of TOP command?

- `VIRT` represents virtual memory, the total amount of virtual memory requested / used by the task. It includes all code, data and shared libraries  plus  pages  that have  been  swapped out and pages that have been mapped but not used.
- `RES` represents resident memory, the non-swapped physical memory a task has used.
- `SHR` represents shared memory, the amount of shared memory used by a task.  It simply reflects memory that could be potentially shared with other processes.
