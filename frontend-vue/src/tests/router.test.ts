import { createRouter, createWebHistory } from 'vue-router';
import { routes } from '../router/index';


describe('Router', () => {
    let router: ReturnType<typeof createRouter>;

    beforeEach(() => {
        router = createRouter({
            history: createWebHistory(),
            routes,
        });
    });

    it('should navigate to login page', async () => {
        router.push('/login');
        await router.isReady();
        expect(router.currentRoute.value.name).toBe('login');
    });

    it('should redirect from root to login page', async () => {
        router.push('/');
        await router.isReady();
        expect(router.currentRoute.value.name).toBe('login');
    });

    it('should navigate to register page', async () => {
        router.push('/register');
        await router.isReady();
        expect(router.currentRoute.value.name).toBe('register');
    });

    it('should navigate to user home page', async () => {
        router.push('/user');
        await router.isReady();
        expect(router.currentRoute.value.name).toBe('user');
    });

    it('should navigate to admin management page', async () => {
        router.push('/admin');
        await router.isReady();
        expect(router.currentRoute.value.name).toBe('admin');
    });

    it('should navigate to admin user pagination component', async () => {
        router.push('/admin/users');
        await router.isReady();
        expect(router.currentRoute.value.name).toBe('admin-userpage');
    });


});
