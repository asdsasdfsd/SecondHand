// vitest.setup.ts
import { vi } from 'vitest';

// mock CSS imports
vi.mock('*.css', () => ({}));
